package com.maxfriends.back.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.FirebaseDatabase;
import com.maxfriends.back.converter.GenericConverter;
import com.maxfriends.back.dto.FriendDto;
import com.maxfriends.back.dto.PasswordDto;
import com.maxfriends.back.entity.Friend;
import com.maxfriends.back.entity.Sortie;
import com.maxfriends.back.firebase.FirebaseConversion;
import com.maxfriends.back.firebase.FirebaseRequest;
import com.maxfriends.back.firebase.FirebaseService;
import com.maxfriends.back.firebase.FirebaseUpdate;
import com.maxfriends.back.repository.FriendRepository;
import com.maxfriends.back.service.IFriendService;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service(value = "friendService")
public class FriendServiceImpl implements UserDetailsService, IFriendService {

    private LogsInformations logsInformations = new LogsInformations();

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    GenericConverter<Friend, FriendDto> friendConverter;

    FirebaseConversion firebaseConversion = new FirebaseConversion();

    public Collection<FriendDto> getAll() {
        Collection<FriendDto> collection = Collections.EMPTY_LIST;
        logsInformations.affichageLogDate("Récupération de la liste des amis");
        try {
            collection = friendConverter.entitiesToDtos(this.friendRepository.findAll(), FriendDto.class);
            logsInformations.sendToFirebase(getClass().getName(), "", true);
        } catch (Exception e) {
            logsInformations.affichageLogDate(e.getMessage());
            logsInformations.sendToFirebase(getClass().getName(), "", false);
            collection = Collections.EMPTY_LIST;
        }
        return collection;
    }

    public FriendDto loadById(String id) {
        FriendDto friendDto;
        logsInformations.affichageLogDate("Récupération des données du n°" + id);
        try {
            friendDto = this.friendConverter.entityToDto(this.friendRepository.getOne(Long.parseLong(id)), FriendDto.class);
            logsInformations.sendToFirebase(getClass().getName(), id, true);
        } catch (Exception e) {
            friendDto = null;
            logsInformations.sendToFirebase(getClass().getName(), id, false);
        }
        return friendDto;
    }

    public Friend createFriend(FriendDto dto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Friend friend = friendConverter.dtoToEntity(dto, Friend.class);
        friend.setPassword(encoder.encode(dto.getPassword()));

        try {
            this.friendRepository.save(friend);
            logsInformations.sendToFirebase(getClass().getName(), dto.toString(), true);
            Friend friendToFirebase = friendConverter.dtoToEntity(dto, Friend.class);
            UserRecord user = FirebaseAuth.getInstance().createUser(firebaseConversion.convertFriendToUser(friendToFirebase));
            Map<String, String> map  = new HashMap<String, String>() {{
                put("uid", user.getUid());
                put("prenom", friend.getPrenom());
            }};
            FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).setValueAsync(map);
            logsInformations.affichageLogDate("Création compte client de " + dto.getPrenom());
            return friend;
        } catch (Exception e) {
            logsInformations.affichageLogDate("Erreur lors de la création de compte : " + e.getMessage());
            logsInformations.sendToFirebase(getClass().getName(), dto.toString(), false);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean uploadImgaeToDB(MultipartFile imageFile, Long friendId) {
        boolean retourUpload;
        List<String> args = Arrays.asList(imageFile.getName(), friendId.toString());
        Optional<Friend> optionalFriend = friendRepository.findById(friendId);
        if (optionalFriend.isPresent()) {
            Friend f = optionalFriend.get();
            logsInformations.affichageLogDate("Ami " + f.getPrenom() + " trouvé !");
            try {
                byte[] imageArr = imageFile.getBytes();
                f.setProfileImage(imageArr);
                logsInformations.affichageLogDate("Image pour " + f.getPrenom() + " ajouté !");
                friendRepository.save(f);
                logsInformations.sendToFirebase(getClass().getName(), args.toString(), true);
                retourUpload = true;
            } catch (IOException e) {
                retourUpload = false;
                logsInformations.sendToFirebase(getClass().getName(), args.toString(), false);
                e.printStackTrace();
            }
        } else {
            logsInformations.affichageLogDate("Ami " + friendId + " non trouvé !");
            logsInformations.sendToFirebase(getClass().getName(), args.toString(), false);
            retourUpload = false;
        }

        return retourUpload;
    }

    public Friend updateUser(FriendDto friendDto) {
        Friend friendUpdated = null;
        try {
            Optional<Friend> optionalFriend = this.friendRepository.findById(friendDto.getId());
            if(optionalFriend.isPresent()) {
                Friend friend = optionalFriend.get();
                logsInformations.affichageLogDate("Ami avec id " + friendDto.getId() + " trouvé ==> " + friend.getPrenom()  + " !");
                friend.setLogin(friendDto.getLogin());
                friend.setPrenom(friendDto.getPrenom());
                friend.setEmail(friendDto.getEmail());
                this.friendRepository.save(friend);
                logsInformations.affichageLogDate("Données de l'utilisateur " + friend.getPrenom() + " modifié avec succès !");
                friendUpdated = friend;
                logsInformations.sendToFirebase(getClass().getName(), friendDto.toString(), true);
            } else {
                logsInformations.affichageLogDate("Ami avec id " + friendDto.getId() + " non trouvé !");
                logsInformations.sendToFirebase(getClass().getName(), friendDto.toString(), false);
            }
        } catch (Exception e) {
            logsInformations.affichageLogDate("Erreur rencontré : " + e.getMessage());
            logsInformations.sendToFirebase(getClass().getName(), friendDto.toString(), false);
        }
        return friendUpdated;
    }

    public boolean resetPassword(PasswordDto dto) {
        Friend friend = this.friendRepository.findByLogin(dto.getUserLogin());
        boolean retourFonction;
        if (friend == null) {
            logsInformations.affichageLogDate("Username ou password incorrect");
            logsInformations.sendToFirebase(getClass().getName(), dto.toString(), false);
            throw new UsernameNotFoundException("Invalid username or password !");
        } else {
            BCryptPasswordEncoder encoderNew = new BCryptPasswordEncoder();
            if(dto.getToken().equals(friend.getCodeMdp())) {
                friend.setPassword(encoderNew.encode(dto.getNewPassword()));
                friend.setMdpProvisoire(false);
                friend.setCodeMdp("");
                friend.setDateExpiration(null);
                try {
                    this.friendRepository.save(friend);
                    retourFonction = true;
                    logsInformations.sendToFirebase(getClass().getName(), dto.toString(), true);
                    logsInformations.affichageLogDate("Mot de passe pour " + friend.getLogin() + " modifié avec succès !");
                } catch (Exception e) {
                    retourFonction = false;
                    logsInformations.affichageLogDate("PB avec la modification du mot de passe : " + e.getMessage());
                    logsInformations.sendToFirebase(getClass().getName(), dto.toString(), false);
                }
            } else {
                logsInformations.affichageLogDate("Token incorrect !");
                logsInformations.sendToFirebase(getClass().getName(), dto.toString(), false);
                throw new UsernameNotFoundException("Token incorrect !");
            }
        }
        return retourFonction;
    }

    public boolean checkingTempPassword(String username, String tempString) {
        boolean retourChecking;
        List<String> args = Arrays.asList(username, tempString);
        Friend friend = this.friendRepository.findByLogin(username);
        LocalDateTime date = LocalDateTime.now();
        if(friend==null) {
            logsInformations.affichageLogDate("Ami " + username + " non trouvé !");
            logsInformations.sendToFirebase(getClass().getName(), args.toString(), false);
            retourChecking = false;
        } else {
            if(!friend.isMdpProvisoire()) {
                logsInformations.affichageLogDate("Client " + friend.getPrenom() + " n'a pas demandé de réinitialisation !");
                retourChecking = false;
                logsInformations.sendToFirebase(getClass().getName(), args.toString(), false);
            } else if (friend.isMdpProvisoire() && !tempString.equals(friend.getCodeMdp())) {
                logsInformations.affichageLogDate("Autorisation de modification de MDP non accordé pour " + friend.getPrenom());
                retourChecking = false;
                logsInformations.sendToFirebase(getClass().getName(), args.toString(), false);
            } else {
                if(friend.getDateExpiration().isBefore(date)) {
                    logsInformations.affichageLogDate("Date d'expiration dépassé !");
                    retourChecking = false;
                    logsInformations.sendToFirebase(getClass().getName(), args.toString(), false);
                } else {
                    logsInformations.affichageLogDate("Autorisation de modification de MDP accordé pour " + friend.getPrenom());
                    retourChecking = true;
                    logsInformations.sendToFirebase(getClass().getName(), args.toString(), true);
                }
            }
        }
        return retourChecking;
    }

    public boolean getForgotPassword(String u) {
        boolean mailEnvoye = false;
        Friend friend = this.friendRepository.findByLogin(u);
        if(friend==null) {
            logsInformations.affichageLogDate("Ami non trouvé !");
            logsInformations.sendToFirebase(getClass().getName(), u, false);
        } else {
            logsInformations.affichageLogDate("Ami " + friend.getPrenom() + " identifié !");
            String email = friend.getEmail();
            String from = "monsite059@gmail.com";

            final String username = "monsite059@gmail.com";
            final String password = "pkjiafqgpasxwckj";

            Properties prop = System.getProperties();

            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            if(email==null || email.equals("")) {
                logsInformations.affichageLogDate("Ami " + friend.getPrenom() + " n'a pas d'adresse mail");
                mailEnvoye = false;
                logsInformations.sendToFirebase(getClass().getName(), u, false);
            } else {
                try {
                    String codeProvisoireClient = logsInformations.getRandomPassword(20);
                    friend.setCodeMdp(codeProvisoireClient);
                    friend.setMdpProvisoire(true);
                    friend.setDateExpiration(LocalDateTime.now().plusMinutes(10));
                    this.friendRepository.save(friend);
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                    message.setSubject("FRIEND App - Demande de réinitialisation de mot de passe");
                    message.setText("Voici votre mot de passe temporaire : " + codeProvisoireClient);

                    Transport.send(message);
                    logsInformations.affichageLogDate("Sent message successfully....");
                    mailEnvoye=true;
                    logsInformations.sendToFirebase(getClass().getName(), u, true);
                } catch (MessagingException mex) {
                    logsInformations.sendToFirebase(getClass().getName(), u, false);
                    mex.printStackTrace();
                    mailEnvoye=false;
                }
            }
        }

        return mailEnvoye;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Friend friend = this.friendRepository.findByLogin(username);
        if (friend == null) {
            logsInformations.affichageLogDate("Username ou password incorrect");
            throw new UsernameNotFoundException("Invalid username or password !");
        }
        return new User(friend.getLogin(), friend.getPassword(), this.getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public Collection<Sortie> getSortiesOfFriend(Long id) {
        Optional<Friend> friend = this.friendRepository.findById(id);
        Collection<Sortie> listeSorties = new ArrayList<>(Collections.emptyList());
        if(friend.isPresent()) {
            logsInformations.affichageLogDate("Ami " + friend.get().getPrenom() + " trouvé !");
            listeSorties.addAll(friend.get().getSorties());
            logsInformations.sendToFirebase(getClass().getName(), id.toString(), true);
        } else {
            logsInformations.affichageLogDate("Ami avec id n°" + id + " non trouvé !");
            logsInformations.sendToFirebase(getClass().getName(), id.toString(), false);
        }
        return listeSorties;
    }
}

package my_project.dto;

public class MusicianDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private String musicStyle;
    
    public MusicianDTO() {
    }
    public MusicianDTO(Long id, String firstName, String lastName, String bio, String musicStyle) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.musicStyle = musicStyle;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getMusicStyle() {
        return musicStyle;
    }
    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }
    @Override
    public String toString() {
        return "MusicianDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio
                + ", musicStyle=" + musicStyle + "]";
    }

    
}

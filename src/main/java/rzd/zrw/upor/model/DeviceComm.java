package rzd.zrw.upor.model;

import java.time.LocalDateTime;

public class DeviceComm extends AbstractBaseEntity {
    private LocalDateTime dateTime;
    private String registrationList;
    private String typeOfCommunication;
    private String location;
    private String defect;
    private String description;
    private String responsibleEmployees;
    private Boolean status;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
    private User user;

    public DeviceComm() {
    }

    public DeviceComm(LocalDateTime dateTime, String registrationList, String typeOfCommunication, String location,
                      String defect, String description, String responsibleEmployees, Boolean status) {
        this(null, dateTime, registrationList, typeOfCommunication, location,
                defect, description, responsibleEmployees, status);
    }

    public DeviceComm(Integer id, LocalDateTime dateTime, String registrationList, String typeOfCommunication, String location, String defect, String description, String responsibleEmployees, Boolean status) {
        super(id);
        this.dateTime = dateTime;
        this.registrationList = registrationList;
        this.typeOfCommunication = typeOfCommunication;
        this.location = location;
        this.defect = defect;
        this.description = description;
        this.responsibleEmployees = responsibleEmployees;
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(String registrationList) {
        this.registrationList = registrationList;
    }

    public String getTypeOfCommunication() {
        return typeOfCommunication;
    }

    public void setTypeOfCommunication(String typeOfCommunication) {
        this.typeOfCommunication = typeOfCommunication;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibleEmployees() {
        return responsibleEmployees;
    }

    public void setResponsibleEmployees(String responsibleEmployees) {
        this.responsibleEmployees = responsibleEmployees;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DeviceComm{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", registrationList='" + registrationList + '\'' +
                ", typeOfCommunication='" + typeOfCommunication + '\'' +
                ", location='" + location + '\'' +
                ", defect='" + defect + '\'' +
                ", description='" + description + '\'' +
                ", responsibleEmployees='" + responsibleEmployees + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

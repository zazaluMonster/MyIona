package iona.modelView;


import iona.pojo.Crew;
import iona.util.MyHttpStatus;

import java.util.List;

public class CrewResponse extends BaseModelView {
    private String token;
    private Crew crew;
    private List<Crew> crewList;
    private String avatarURL;

    public CrewResponse(MyHttpStatus status, String token) {
        super(status);
        this.token = token;
    }

    public CrewResponse(MyHttpStatus status) {
        super(status);
    }


    public CrewResponse(MyHttpStatus status, List<Crew> crewList) {
        super(status);
        this.crewList = crewList;
    }

    public CrewResponse(MyHttpStatus status, Crew crew) {
        super(status);
        this.crew = crew;
        this.crew.setPasswordMd5("");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}

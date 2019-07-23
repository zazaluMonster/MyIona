package iona.modelView;


import iona.pojo.Crew;
import iona.util.MyHttpStatus;

import java.util.List;

public class CrewModelView extends BaseModelView {
    private String token;
    private List<Crew> crewList;

    public CrewModelView(MyHttpStatus status, String msg, String errMsg, String token) {
        super(status, msg, errMsg);
        this.token = token;
    }

    public CrewModelView(MyHttpStatus status) {
        super(status);
    }

    public CrewModelView(MyHttpStatus status, List<Crew> crewList) {
        super(status);
        this.crewList = crewList;
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
}

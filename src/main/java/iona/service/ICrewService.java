package iona.service;


import iona.exception.FirstLoginException;
import iona.pojo.Crew;

public interface ICrewService extends BaseService<Crew> {
    public boolean auth(Crew crew);
    public String tokenResolve(Crew crew, boolean doForce) throws FirstLoginException;
    public boolean isCrewAlreadyExist(String crewName, String phoneNum, String mail);
    public int register(Crew crew);
}

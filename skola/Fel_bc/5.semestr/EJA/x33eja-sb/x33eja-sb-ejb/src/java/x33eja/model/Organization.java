package x33eja.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;

@Entity
public class Organization {

    @OneToMany(mappedBy="mIsMemberOf")
    private List<Teacher> mHasMember;

    @Id
    private int deptId;

    private String name;

    public int getDeptId () {
        return deptId;
    }

    public void setDeptId (int val) {
        this.deptId = val;
    }

    public String getName () {
        return name;
    }

    public void setName (String val) {
        this.name = val;
    }

    public List<Teacher> getHasMember () {
        return mHasMember;
    }

    public void setHasMember (List<Teacher> val) {
        this.mHasMember = val;
    }
}


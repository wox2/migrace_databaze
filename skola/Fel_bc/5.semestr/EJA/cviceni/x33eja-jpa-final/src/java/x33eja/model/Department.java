package x33eja.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Department {

    /**
     * Pro ciselny klic pouzit automaticke generovani napr. sekvencemi tzn.
     * misto Auto, kdy se ponecha volba na databazi, zvolit GenerationType.SEQUENCE
     * a nasledne jeste definovat jmeno sekvence, jinak bude pouzita vychozi
     * - spolecna pro vsechny entity
     * @GeneratedValue(strategy=GenerationType.SEQUENCE)
     * @SequenceGenerator(sequenceName="seq_department")
     *
     * anotace Column je volitelna
     */

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int deptId;

    private String name;

    /**
     * Povsimnete si, u ktere relace je v tomto pripade umisten cizi klic
     */
    @OneToMany(mappedBy="mIsMemberOf")
    private List<Teacher> mHasMember;

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


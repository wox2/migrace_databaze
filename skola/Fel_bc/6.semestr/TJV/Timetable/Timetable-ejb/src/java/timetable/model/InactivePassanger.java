/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author woxie
 */
@Entity

@DiscriminatorValue("inactive")
@NamedQuery(name = InactivePassanger.Q_GET_ALL_INACTIVES, query = "SELECT p FROM InactivePassanger p")
public class InactivePassanger extends Person {

    @Transient
    public static final String Q_GET_ALL_INACTIVES = "InactivePassanger.getAll";

}

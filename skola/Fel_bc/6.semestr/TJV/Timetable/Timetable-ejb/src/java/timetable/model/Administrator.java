/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author woxie
 */
@Entity
@DiscriminatorValue("administrator")
public class Administrator extends Person implements Serializable {

}

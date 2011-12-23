/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import timetable.model.Note;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author woxie
 */
@ManagedBean(name="notes")
@SessionScoped
public class NotesBean {

    @EJB
    private TimetableCenterLocal tc;

    private Note note = null;

    /** Creates a new instance of NoteBean */
    public NotesBean() {
    }

    public String newNote() {
        note = new Note();
        return "note";
    }

    public List<Note> getAllNotes() {
        return tc.getAllNotes();
    }


    public String saveNote() {
        tc.updateNote(note);
        return "notes";
    }

    public String editNote(Note Note) {
        this.note = Note;
        return "note";
    }

    public void removeNote(Note Note) {
        tc.removeNote(Note);
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note Note) {
        this.note = Note;
    }

    
}

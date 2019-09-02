package integration;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.ListCell;

public class PreguntaCell extends ListCell<Pregunta> {

    static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");

    @Override
    protected void updateItem(Pregunta pregunta, boolean vacia) {
        super.updateItem(pregunta, vacia);
        if (vacia) {
            setText("");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(sdf.format(new Date(pregunta.getCuando()))).append("]")
                    .append(" ").append(pregunta.getQuien() + ": " + pregunta.getQue());
            setText(sb.toString());
        }
    }
}

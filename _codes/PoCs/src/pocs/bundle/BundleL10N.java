package pocs.bundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase utilitaria para gestionar la Localización (BundleL10N)
 */
public final class BundleL10N {

    private static final ObjectProperty<Locale> locale;

    static {
        locale = new SimpleObjectProperty<>(getLocalePredeterminado());
        locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
    }

    /**
     * Listado con los Locale s soportados
     *
     * @return Lista de objetos Locale
     */
    public static List<Locale> getLocalesSoportados() {
        return new ArrayList<>(Arrays.asList(Locale.ENGLISH, Locale.FRENCH, new Locale("es")));
    }

    /**
     * Retorna el Local predeterminado, o de no encontrarse uno, 'en'
     *
     * @return Una referencia al objeto Locale predeterminado
     */
    public static Locale getLocalePredeterminado() {
        Locale localDelSistema = Locale.getDefault();
        return getLocalesSoportados().contains(localDelSistema) ? localDelSistema : Locale.ENGLISH;
    }

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        localeProperty().set(locale);
        Locale.setDefault(locale);
    }

    public static ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    /**
     * Obtiene la cadena correspondiente a la llave pasada. La cadena se carga
     * del archivo de mensajes con la localización adecuada
     *
     * @param key llave del archivo de mensajes
     * @param args argumentos para el mensaje (opcionales)
     * @return la cadena del archivo de mensajes localizado
     */
    public static String get(final String key, final Object... args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pocs/bundle/mensajes", getLocale());
        return MessageFormat.format(resourceBundle.getString(key), args);
    }

    /**
     * Crea un StringBinding para una cadena asociada a una llave de un resource
     * bundle
     *
     * @param key llave en el archivo de propiedades
     * @param args Argumentos para mensajes con formato {0}, {1}, etc...
     * @return Cadena asociada (StringBinding)
     */
    public static StringBinding creaStringBinding(final String key, Object... args) {
        return Bindings.createStringBinding(() -> get(key, args), locale);
    }

}

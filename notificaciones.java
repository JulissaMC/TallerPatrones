interface Notificacion {
    void enviarContenido(Reporte reporte, String destinatario);
}

class NotificacionClase implements Notificacion {
    protected Reporte reporte;

    public NotificacionClase(Reporte reporte) {
        this.reporte = reporte;
    }

    @Override
    public void enviarContenido(Reporte reporte, String destinatario) {
        System.out.println("Enviando notificación con reporte a " + destinatario);
        reporte.mostrar();
    }
}


abstract class EnvioDecorator implements Notificacion {
    protected Notificacion instanciaNotificacion;

    public EnvioDecorator(Notificacion instanciaNotificacion) {
        this.instanciaNotificacion = instanciaNotificacion;
    }

    @Override
    public void enviarContenido(Reporte reporte, String destinatario) {
        instanciaNotificacion.enviarContenido(reporte, destinatario);
    }
}


class Correo extends EnvioDecorator {
    private String email;

    public Correo(Notificacion notificacion, String email) {
        super(notificacion);
        this.email = email;
    }

    public void escribirContenido() {
        System.out.println("Preparando contenido de correo para: " + email);
    }

    @Override
    public void enviarContenido(Reporte reporte, String destinatario) {
        escribirContenido();
        System.out.println("Enviando correo a " + destinatario);
        super.enviarContenido(reporte, destinatario);
    }
}

class WhatsApp extends EnvioDecorator {
    private String telefono;
    private boolean esAudio;// por si el reporte se envia en forma de audio, igual el programa el ejecutarse lo enviará

    public WhatsApp(Notificacion notificacion, String telefono, boolean esAudio) {
        super(notificacion);
        this.telefono = telefono;
        this.esAudio = esAudio;
    }

    public void escribirContenido() {
        System.out.println("Preparando contenido de WhatsApp para el número: " + telefono);
    }

    @Override
    public void enviarContenido(Reporte reporte, String destinatario) {
        escribirContenido();
        if (esAudio) {
            System.out.println("Enviando un mensaje de voz por WhatsApp a " + destinatario);
        } else {
            System.out.println("Enviando mensaje de texto por WhatsApp a " + destinatario);
        }
        super.enviarContenido(reporte, destinatario);
    }
}


class Telegram extends EnvioDecorator {
    private String telefono;

    public Telegram(Notificacion notificacion, String telefono) {
        super(notificacion);
        this.telefono = telefono;
    }

    public void escribirContenido() {
        System.out.println("Preparando contenido de Telegram para el número: " + telefono);
    }

    @Override
    public void enviarContenido(Reporte reporte, String destinatario) {
        escribirContenido();
        System.out.println("Enviando mensaje de Telegram a " + destinatario);
        super.enviarContenido(reporte, destinatario);
    }
}


public class Main {
    public static void main(String[] args) {
        
        Reporte reporte = new Reporte(); //reporte de prueba
        reporte.agregarEncabezado("Reporte de Votaciones de Estados Unidos");
        reporte.agregarCuerpo(" cuerpo");
        reporte.agregarPie("Gracias");

        
        Notificacion notificacionBase = new NotificacionClase(reporte);

        
        Notificacion correo = new Correo(notificacionBase, "usuario@example.com");
        correo.enviarContenido(reporte, "usuario@example.com");

        
        Notificacion whatsapp = new WhatsApp(notificacionBase, "+123456789", false);
        whatsapp.enviarContenido(reporte, "usuario_telefono");

        
        Notificacion telegram = new Telegram(notificacionBase, "+987654321");
        telegram.enviarContenido(reporte, "usuario_telefono");
    }
}

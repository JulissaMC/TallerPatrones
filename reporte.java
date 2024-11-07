// Reporte.java

public class Reporte {
    private String encabezado;
    private String cuerpo;
    private String pie;

    public void agregarEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public void agregarCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void agregarPie(String pie) {
        this.pie = pie;
    }

    public void generar() {
        System.out.println("Generando reporte:");
        System.out.println("Encabezado: " + encabezado);
        System.out.println("Cuerpo: " + cuerpo);
        System.out.println("Pie: " + pie);
    }

    public void mostrar() {
        System.out.println("Mostrando reporte:");
        System.out.println("Encabezado: " + encabezado);
        System.out.println("Cuerpo: " + cuerpo);
        System.out.println("Pie: " + pie);
    }
}

abstract class ReporteBuilder {
    protected Reporte reporte;

    public Reporte getReporte() {
        return reporte;
    }

    public void crearNuevoReporte() {
        reporte = new Reporte();
    }

    public abstract void crearEncabezado();
    public abstract void crearCuerpo();
    public abstract void crearPie();
}

class ReportePDFBuilder extends ReporteBuilder {
    @Override
    public void crearEncabezado() {
        reporte.agregarEncabezado("Encabezado PDF");
    }

    @Override
    public void crearCuerpo() {
        reporte.agregarCuerpo("Cuerpo del repiorte en PDF");
    }

    @Override
    public void crearPie() {
        reporte.agregarPie("Pie de pagina PDF");
    }
}

class ReporteExcelBuilder extends ReporteBuilder {
    @Override
    public void crearEncabezado() {
        reporte.agregarEncabezado("Encabesado en Excel");
    }

    @Override
    public void crearCuerpo() {
        reporte.agregarCuerpo("Cuerpo del reporte en Excel");
    }

    @Override
    public void crearPie() {
        reporte.agregarPie("Pie de pagina Excel");
    }
}

class ReporteWordBuilder extends ReporteBuilder {
    @Override
    public void crearEncabezado() {
        reporte.agregarEncabezado("Encabezado en Word");
    }

    @Override
    public void crearCuerpo() {
        reporte.agregarCuerpo("Cuerpo del reporte en Word");
    }

    @Override
    public void crearPie() {
        reporte.agregarPie("Pie de pagina Word");
    }
}

class Director {
    private ReporteBuilder builder;

    public void setBuilder(ReporteBuilder builder) {
        this.builder = builder;
    }

    public void construirReporte() {
        builder.crearNuevoReporte();
        builder.crearEncabezado();
        builder.crearCuerpo();
        builder.crearPie();
    }
}

class Main {
    public static void main(String[] args) {
        Director director = new Director();

        // generar reporte en PDF
        ReporteBuilder pdfBuilder = new ReportePDFBuilder();
        director.setBuilder(pdfBuilder);
        director.construirReporte();
        Reporte reportePDF = pdfBuilder.getReporte();
        reportePDF.mostrar();

        // generar reporte en Excel
        ReporteBuilder excelBuilder = new ReporteExcelBuilder();
        director.setBuilder(excelBuilder);
        director.construirReporte();
        Reporte reporteExcel = excelBuilder.getReporte();
        reporteExcel.mostrar();

        // generar reporte en Word
        ReporteBuilder wordBuilder = new ReporteWordBuilder();
        director.setBuilder(wordBuilder);
        director.construirReporte();
        Reporte reporteWord = wordBuilder.getReporte();
        reporteWord.mostrar();
    }
}

interface Informe{
    void generar();
}

class InformeBasico implements Informe{
    @Override
    public void generar(){
        System.out.println("Se está generando");
    }
}

abstract class InformeDecorator implements Informe{
    protected Informe informe;

    public InformeDecorator(Informe informe){
        this.informe = informe;
    }

    @Override
    public void generar(){
        informe.generar();
        System.out.println("Se está generando");
    }
}

class EstiloDecorator extends InformeDecorator{
    public EstiloDecorator(Informe informe){
        super(informe);
    }

    @Override
    public void generar(){
        super.generar();
        System.out.println("Se está generando");
    }
}

class FuenteDecorator extends InformeDecorator{
    public FuenteDecorator(Informe informe){
        super(informe);
    }

    @Override
    public void generar(){
        super.generar();
        System.out.println("Se está generando");
    }
}

class ColorDecorator extends InformeDecorator{
    public ColorDecorator(Informe informe){
        super(informe);
    }

    @Override
    public void generar(){
        super.generar();
        System.out.println("Se está generando");
    }
}

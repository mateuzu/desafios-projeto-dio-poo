package exceptions;

public class ExcecaoGlobal extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExcecaoGlobal(String mensagem) {
		super(mensagem);
	}

}

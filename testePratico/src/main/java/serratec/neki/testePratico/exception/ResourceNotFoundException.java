package serratec.neki.testePratico.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}

}

package serratec.neki.testePratico.exception;


public class ResourceBadRequestException extends RuntimeException {
	public ResourceBadRequestException(String mensagem) {
		super(mensagem);
	}
}

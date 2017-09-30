import service.ServerService;

public class Main {

	public static void main(String[] args) {
		System.out.println("Client main init");
		ServerService.getInstance().startProgram();

	}

}

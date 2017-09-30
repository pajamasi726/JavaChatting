import Service.ResourceService;

public class Main {
	
	public static ResourceService resourceService;

	public static void main(String[] args) {
		System.out.println("main init");

		resourceService = new ResourceService();
		
	}

}

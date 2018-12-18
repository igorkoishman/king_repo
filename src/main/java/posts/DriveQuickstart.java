package posts;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import com.google.common.collect.Lists;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class DriveQuickstart {
	private static final String APPLICATION_NAME = "Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart.
	 * If modifying these scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Lists.newArrayList(DriveScopes.DRIVE);
	private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = DriveQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline")
				.build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("igorkoishman@gmail.com");
	}

	public static void main(String... args) throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME)
				.build();

//		 Print the names and IDs for up to 10 files.
		FileList result = service.files().list()
				.setPageSize(10)
				.setFields("nextPageToken, files(id, name)")
				.execute();
		List<File> files = result.getFiles();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				System.out.printf("%s (%s)\n", file.getName(), file.getId());
			}
		}
//		read(service);
		uploadFile(service);
//		String fileId="1QNRT70TOwvRQh3EM_cp8h9S4kS8HSpXM";
//		downloadFile(service,"file_name",fileId);
		}

	private static void read(Drive service) throws IOException {
		File fileMetadata = new File();
		fileMetadata.setName("CR9A8913.jpg");
		java.io.File filePath = new java.io.File("CR9A8913.jpg");
		FileContent mediaContent = new FileContent("image/jpeg", filePath);
		File file = service.files().create(fileMetadata, mediaContent)
				.setFields("id")
				.execute();
		System.out.println("File ID: " + file.getId());
	}

	private static void uploadFile(Drive service) throws IOException {
		String folderId = "1xGzafiSwK-egd_KqdvwN1BH1fhPhaFzn";
		File fileMetadata = new File();
		fileMetadata.setName("video");
		fileMetadata.setParents(Lists.newArrayList(folderId));
		java.io.File filePath = new java.io.File("video.mp4");
		FileContent mediaContent = new FileContent("application/octet-streamg", filePath);
		File file = service.files().create(fileMetadata, mediaContent)
				.setFields("id")
				.execute();
		System.out.println("File ID: " + file.getId());
	}

	private static void downloadFile(Drive service,String fileName,String fileId) throws IOException {
		InputStream get = service.files().get("1QNRT70TOwvRQh3EM_cp8h9S4kS8HSpXM").executeMediaAsInputStream();

		try {

				FileOutputStream output = new FileOutputStream(
						fileName);
				try {
					int l;
					byte[] tmp = new byte[2048];
					while ((l = get.read(tmp)) != -1) {
						output.write(tmp, 0, l);
					}
				} finally {
					output.close();
					get.close();
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printPermission(Drive service, String fileId,
			String permissionId) {
		try {
			Permission permission = service.permissions().get(
					fileId, permissionId).execute();

			System.out.println("Name: " + permission.getDisplayName());
			System.out.println("Role: " + permission.getRole());
			for (Permission.TeamDrivePermissionDetails additionalRole : permission.getTeamDrivePermissionDetails()) {
				System.out.println("Additional role: " + additionalRole);
			}
		} catch (IOException e) {
			System.out.println("An error occurred: " + e);
		}
	}

}
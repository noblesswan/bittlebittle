//package com.spring.bittlebittle.google;
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.auth.oauth2.TokenResponseException;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.security.GeneralSecurityException;
//import java.util.Arrays;
//import java.util.Collections;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class GoogleAuthorization {
//    private static final String APPLICATION_NAME = "My Application";
//    private static final String CLIENT_SECRET_FILE = "/client_secret.json";
//    private static final String[] SCOPES = {"email", "profile"};
//
//    private static GoogleIdTokenVerifier verifier;
//    private static GoogleAuthorizationCodeFlow flow;
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//
//    private static String getRedirectUri() {
//        return "http://localhost:8080/callback";
//    }
//
//    static {
//        try {
//            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
//                    JSON_FACTORY, new InputStreamReader(
//                            GoogleAuthorization.class.getResourceAsStream(CLIENT_SECRET_FILE)));
//
//            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport,
//                    JSON_FACTORY, clientSecrets,
//                    Arrays.asList(SCOPES)).build();
//
//            verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
//                    .setAudience(Collections.singletonList(clientSecrets.getDetails().getClientId()))
//                    .build();
//
//        } catch (IOException | GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String getAuthorizationUrl() {
//        return flow.newAuthorizationUrl().setRedirectUri(getRedirectUri()).build();
//    }
//
//    public static Credential getCredential(String code) throws IOException {
//        try {
//            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
//            GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(code).setRedirectUri(getRedirectUri());
//            GoogleTokenResponse tokenResponse = tokenRequest.execute();
//            return flow.createAndStoreCredential(tokenResponse, null);
//        } catch (TokenResponseException e) {
//            throw new IOException("Failed to retrieve access token: " + e.getMessage(), e);
//        }
//    }
//
//    public static Payload getPayload(HttpServletRequest request) throws IOException {
//        Credential credential = getCredential(request.getParameter("code"));
//        GoogleIdToken idToken = null;
//        try {
//            idToken = verifier.verify(credential.getAccessToken());
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return idToken.getPayload();
//    }
//
//    public static Payload verifyIdToken(Credential credential) throws IOException {
//        try {
//            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
//                    JSON_FACTORY, new InputStreamReader(
//                            GoogleAuthorization.class.getResourceAsStream(CLIENT_SECRET_FILE)));
//            verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
//                    .setAudience(Collections.singletonList(clientSecrets.getDetails().getClientId()))
//                    .build();
//
//            GoogleIdToken idToken = verifier.verify(credential.getAccessToken());
//            if (idToken != null) {
//                Payload payload = idToken.getPayload();
//                return payload;
//            } else {
//                throw new IOException("Invalid ID token"
//                        ); // ���⼭ Invalid ID token ���ܸ� �߻���ŵ�ϴ�.
//            }
//        } catch (GeneralSecurityException e) {
//            throw new IOException("Failed to create ID token verifier.", e);
//        } catch (IOException e) {
//            throw new IOException("Failed to load client secrets.", e);
//        }
//    }
//
//    public static String getEmail(Payload payload) {
//        return payload.getEmail();
//    }
//
//    public static String getName(Payload payload) {
//        return (String) payload.get("name");
//    }
//
//    public static String getPictureUrl(Payload payload) {
//        return (String) payload.get("picture");
//    }
//}
//
//

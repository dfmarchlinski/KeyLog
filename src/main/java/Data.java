import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

class Data {

    Data(String log) throws IOException, ExecutionException, InterruptedException {
        Upload(log);
    }

    private static void Upload(String log) throws IOException, ExecutionException, InterruptedException {

        // Access Firebase database
        InputStream serviceAccount = new FileInputStream("./ServiceAccountKey.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();

        // Upload log to firebase
        DocumentReference ref = db.collection("user").document("data");
        Map<String, String> data = new HashMap<>();
        data.put("log", log);
        String hex = String.format("%x", (int) (Math.random() * 2147483647));
        ApiFuture<WriteResult> result = ref.update(hex, data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
}
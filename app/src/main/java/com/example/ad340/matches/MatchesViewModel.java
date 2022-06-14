package com.example.ad340.matches;

import android.location.Location;

import androidx.core.util.Consumer;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MatchesViewModel {

    private MatchesDataModel matchesDataModel;

    public MatchesViewModel() { matchesDataModel = new MatchesDataModel(); }

    public void getMatches(Location location, float maxDistance, Consumer<ArrayList<Match>> responseCallback) {
        matchesDataModel.getMatches(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<Match> matches = new ArrayList<>();
                        for (DocumentSnapshot todoSnapshot : querySnapshot.getDocuments()) {
                            Match item = todoSnapshot.toObject(Match.class);
                            assert item != null;
                            item.setUid(todoSnapshot.getId());
                            matches.add(item);
                        }

                        ArrayList<Match> filteredMatches = new ArrayList<>();
                        for (Match match : matches) {
                            Location targetLocation = new Location("");
                            targetLocation.setLatitude(Double.parseDouble(match.getLat()));
                            targetLocation.setLongitude(Double.parseDouble(match.getLongitude()));

                            float distance = location.distanceTo(targetLocation);
                            float miles = distance / 1609.344f;

                            if (miles <= maxDistance) {
                                filteredMatches.add(match);
                            }
                        }

                        responseCallback.accept(filteredMatches);
                    }
                },
                (databaseError -> System.out.println("Error reading Todo items: " + databaseError))
        );
    }

    public void updateMatch(Match match)  { matchesDataModel.updateMatchById(match); }

    public void clear() { matchesDataModel.clear(); }

}

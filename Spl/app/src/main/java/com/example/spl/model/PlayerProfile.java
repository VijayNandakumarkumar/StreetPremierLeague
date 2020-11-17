package com.example.spl.model;

import java.util.Objects;

public class PlayerProfile {
    private String playerName;
    private String displayPhoto;
    private String playerNumber;
    private String playerTotalMatchesPlayed;
    private String playerTotalRunsScored;
    private String playerTotalWicketsTaken;
    private String playerStrikeRate;
    private String playerTotalBallsFaced;
    private int playerCurrentMatchScore;
    private int playerCurrentMatchBallsFaced;
    private boolean isAdmin;
    private String uid;

    public PlayerProfile() {

    }

    public PlayerProfile(final String playerName,
                         final String displayPhoto,
                         final String playerNumber,
                         final String playerTotalMatchesPlayed,
                         final String playerTotalRunsScored,
                         final String playerTotalWicketsTaken,
                         final String playerStrikeRate,
                         final int playerCurrentMatchScore,
                         final String uid) {
        this.playerName = playerName;
        this.displayPhoto = displayPhoto;
        this.playerNumber = playerNumber;
        this.playerTotalMatchesPlayed = playerTotalMatchesPlayed;
        this.playerTotalRunsScored = playerTotalRunsScored;
        this.playerTotalWicketsTaken = playerTotalWicketsTaken;
        this.playerStrikeRate = playerStrikeRate;
        this.playerCurrentMatchScore = playerCurrentMatchScore;
        this.uid = uid;
    }

    public String getPlayerTotalBallsFaced() {
        return playerTotalBallsFaced;
    }

    public void setPlayerTotalBallsFaced(final String playerTotalBallsFaced) {
        this.playerTotalBallsFaced = playerTotalBallsFaced;
    }

    public int getPlayerCurrentMatchBallsFaced() {
        return playerCurrentMatchBallsFaced;
    }

    public void setPlayerCurrentMatchBallsFaced(final int playerCurrentMatchBallsFaced) {
        this.playerCurrentMatchBallsFaced = playerCurrentMatchBallsFaced;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(final boolean admin) {
        isAdmin = admin;
    }

    public int getPlayerCurrentMatchScore() {
        return playerCurrentMatchScore;
    }

    public void setPlayerCurrentMatchScore(final int playerCurrentMatchScore) {
        this.playerCurrentMatchScore = playerCurrentMatchScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    public String getDisplayPhoto() {
        return displayPhoto;
    }

    public void setDisplayPhoto(final String displayPhoto) {
        this.displayPhoto = displayPhoto;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(final String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerTotalMatchesPlayed() {
        return playerTotalMatchesPlayed;
    }

    public void setPlayerTotalMatchesPlayed(final String playerTotalMatchesPlayed) {
        this.playerTotalMatchesPlayed = playerTotalMatchesPlayed;
    }

    public String getPlayerTotalRunsScored() {
        return playerTotalRunsScored;
    }

    public void setPlayerTotalRunsScored(final String playerTotalRunsScored) {
        this.playerTotalRunsScored = playerTotalRunsScored;
    }

    public String getPlayerTotalWicketsTaken() {
        return playerTotalWicketsTaken;
    }

    public void setPlayerTotalWicketsTaken(final String playerTotalWicketsTaken) {
        this.playerTotalWicketsTaken = playerTotalWicketsTaken;
    }

    public String getPlayerStrikeRate() {
        return playerStrikeRate;
    }

    public void setPlayerStrikeRate(final String playerStrikeRate) {
        this.playerStrikeRate = playerStrikeRate;
    }

    @Override
    public String toString() {
        return "PlayerProfile{" +
                "playerName='" + playerName + '\'' +
                ", displayPhoto='" + displayPhoto + '\'' +
                ", playerNumber='" + playerNumber + '\'' +
                ", playerTotalMatchesPlayed='" + playerTotalMatchesPlayed + '\'' +
                ", playerTotalRunsScored='" + playerTotalRunsScored + '\'' +
                ", playerTotalWicketsTaken='" + playerTotalWicketsTaken + '\'' +
                ", playerStrikeRate='" + playerStrikeRate + '\'' +
                ", playerCurrentMatchScore=" + playerCurrentMatchScore +
                '}';
    }

    public static class Builder {
        private String playerName;
        private String playerDisplayPhoto;
        private String playerNumber;
        private String playerTotalMatchesPlayed;
        private String playerTotalRunsScored;
        private String playerTotalWicketsTaken;
        private String playerStrikeRate;
        private int playerCurrentMatchScore;
        private String uid;

        public Builder(final String playerName,
                       final String playerDisplayPhoto) {
            this.playerName = playerName;
            this.playerDisplayPhoto = playerDisplayPhoto;

        }

        public Builder withplayerNumber(final String playerNumber) {
            this.playerNumber = playerNumber;
            return this;
        }

        public Builder withTotalMatchesPlayed(final String playerTotalMatchesPlayed) {
            this.playerTotalMatchesPlayed = playerTotalMatchesPlayed;
            return this;
        }

        public Builder withplayerTotalRunsScored(final String playerTotalRunsScored) {
            this.playerTotalRunsScored = playerTotalRunsScored;
            return this;
        }

        public Builder withplayerTotalWicketsTaken(final String playerTotalWicketsTaken) {
            this.playerTotalWicketsTaken = playerTotalWicketsTaken;
            return this;
        }

        private Builder withplayerStrikeRate(final String playerStrikeRate) {
            this.playerStrikeRate = playerStrikeRate;
            return this;
        }

        private Builder withplayerCurrentMatchScore(final int playerCurrentMatchScore) {
            this.playerCurrentMatchScore = playerCurrentMatchScore;
            return this;
        }

        private Builder withUid(final String uid) {
            this.uid = uid;
            return this;
        }

        public PlayerProfile build() {
            final PlayerProfile playerProfile = new PlayerProfile(playerName,
                    playerDisplayPhoto,
                    playerNumber,
                    playerTotalMatchesPlayed,
                    playerTotalRunsScored,
                    playerTotalWicketsTaken,
                    playerStrikeRate,
                    playerCurrentMatchScore,
                    uid);
            return playerProfile;
        }

    }

}

package com.example.spl.model;

import java.util.List;

public class MatchDataModel {
    private int overCount;
    private int teamOnePlayerCount;
    private int teamTwoPlayerCount;
    private List<PlayerProfile> teamOnePlayers;
    private List<PlayerProfile> teamTwoPlayers;

    public MatchDataModel() {

    }

    public MatchDataModel(final int overCount,
                          final int teamOnePlayerCount,
                          final int teamTwoPlayerCount,
                          final List<PlayerProfile> teamOnePlayers,
                          final List<PlayerProfile> teamTwoPlayers) {
        this.overCount = overCount;
        this.teamOnePlayerCount = teamOnePlayerCount;
        this.teamTwoPlayerCount = teamTwoPlayerCount;
        this.teamOnePlayers = teamOnePlayers;
        this.teamTwoPlayers = teamTwoPlayers;
    }

    public int getOverCount() {
        return overCount;
    }

    public void setOverCount(final int overCount) {
        this.overCount = overCount;
    }

    public int getTeamOnePlayerCount() {
        return teamOnePlayerCount;
    }

    public void setTeamOnePlayerCount(final int teamOnePlayerCount) {
        this.teamOnePlayerCount = teamOnePlayerCount;
    }

    public int getTeamTwoPlayerCount() {
        return teamTwoPlayerCount;
    }

    public void setTeamTwoPlayerCount(final int teamTwoPlayerCount) {
        this.teamTwoPlayerCount = teamTwoPlayerCount;
    }

    public List<PlayerProfile> getTeamOnePlayers() {
        return teamOnePlayers;
    }

    public void setTeamOnePlayers(final List<PlayerProfile> teamOnePlayers) {
        this.teamOnePlayers = teamOnePlayers;
    }

    public List<PlayerProfile> getTeamTwoPlayers() {
        return teamTwoPlayers;
    }

    public void setTeamTwoPlayers(final List<PlayerProfile> teamTwoPlayers) {
        this.teamTwoPlayers = teamTwoPlayers;
    }

    public static class Builder {

        private int overCount;
        private int teamOnePlayerCount;
        private int teamTwoPlayerCount;
        private List<PlayerProfile> teamOnePlayers;
        private List<PlayerProfile> teamTwoPlayers;

        public Builder() {

        }

        public Builder withOverCount(final int overCount) {
            this.overCount = overCount;
            return this;
        }

        public Builder withteamOnePlayerCount(final int teamOnePlayerCount) {
            this.teamOnePlayerCount = teamOnePlayerCount;
            return this;
        }

        public Builder withteamTwoPlayerCount(final int teamTwoPlayerCount) {
            this.teamTwoPlayerCount = teamTwoPlayerCount;
            return this;
        }

        public Builder withteamTeamOnePlayers(final List teamOnePlayers) {
            this.teamOnePlayers = teamOnePlayers;
            return this;
        }

        public Builder withteamTeamTwoPlayers(final List teamTwoPlayers) {
            this.teamTwoPlayers = teamTwoPlayers;
            return this;
        }

        public MatchDataModel build() {
            final MatchDataModel matchDataModel = new MatchDataModel(this.overCount,
                    this.teamOnePlayerCount,
                    this.teamTwoPlayerCount,
                    this.teamOnePlayers,
                    this.teamTwoPlayers);
            return matchDataModel;
        }

    }

}

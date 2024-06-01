package com.ranjit.App;

public class HomeDataModal {
    String id;
    String videoCount;
    String playlistTitle;
    String playlistThumbnail;
    String channelName;
    String channelLogo;
    String channelUrl;

    public HomeDataModal(String id, String videoCount, String playlistTitle, String playlistThumbnail, String channelName, String channelLogo, String channelUrl) {
        this.id = id;
        this.videoCount = videoCount;
        this.playlistTitle = playlistTitle;
        this.playlistThumbnail = playlistThumbnail;
        this.channelName = channelName;
        this.channelLogo = channelLogo;
        this.channelUrl = channelUrl;
    }

    public String getId() {
        return id;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public String getPlaylistThumbnail() {
        return playlistThumbnail;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public String getChannelUrl() {
        return channelUrl;
    }
}

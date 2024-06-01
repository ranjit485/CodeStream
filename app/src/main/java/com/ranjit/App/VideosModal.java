package com.ranjit.App;

public class VideosModal {
    String playlistOwner;
    String playlistDescription;
    String playlistThumbnail;
    String playlistId;
    String playlistTitle;
    String videoCount;
    String videoID;
    String videoThumbnail;
    String videoTitle;
    String videoDescription;
    String videoUrl;
    String videoDuration;

    public VideosModal(String playlistOwner, String playlistDescription, String playlistThumbnail, String playlistId, String playlistTitle, String videoCount, String videoID, String videoThumbnail, String videoTitle, String videoDescription, String videoUrl, String videoDuration) {
        this.playlistOwner = playlistOwner;
        this.playlistDescription = playlistDescription;
        this.playlistThumbnail = playlistThumbnail;
        this.playlistId = playlistId;
        this.playlistTitle = playlistTitle;
        this.videoCount = videoCount;
        this.videoID = videoID;
        this.videoThumbnail = videoThumbnail;
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.videoUrl = videoUrl;
        this.videoDuration = videoDuration;
    }

    public String getPlaylistOwner() {
        return playlistOwner;
    }

    public String getPlaylistDescription() {
        return playlistDescription;
    }

    public String getPlaylistThumbnail() {
        return playlistThumbnail;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public String getVideoID() {
        return videoID;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoDuration() {
        return videoDuration;
    }
}
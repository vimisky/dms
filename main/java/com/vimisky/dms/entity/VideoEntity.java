package com.vimisky.dms.entity;

import java.io.Serializable;

public class VideoEntity extends ContentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7121323981889656421L;

	/**
	 *   `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `headline` VARCHAR(1024) NULL COMMENT '标题',
  `description` VARCHAR(2048) NULL COMMENT '描述',
  `icon_href` VARCHAR(255) NULL COMMENT '截图链接',
  `icon_image_type` VARCHAR(255) NULL COMMENT '截图格式',
  `icon_height` INT UNSIGNED NULL COMMENT '截图高度（pixel）',
  `icon_width` INT UNSIGNED NULL COMMENT '截图宽度（pixel）',
  `icon_rendition` VARCHAR(45) NULL COMMENT '截图用途',
  `scripts` TEXT NULL COMMENT '视频脚本',
  `language` VARCHAR(45) NULL COMMENT '脚本语种',
  `href` VARCHAR(255) NULL COMMENT '视频链接',
  `video_mime_type` VARCHAR(255) NULL COMMENT '视频MIME格式',
  `format` VARCHAR(255) NULL COMMENT '格式封装格式',
  `rendition` VARCHAR(255) NULL COMMENT '视频用途',
  `size` INT UNSIGNED NULL COMMENT '视频大小（Byte）',
  `duration` VARCHAR(255) NULL COMMENT '视频时长',
  `duration_unit` VARCHAR(255) NULL COMMENT '视频时长单位',
  `video_codec` VARCHAR(255) NULL COMMENT '视频编码',
  `video_framerate` VARCHAR(255) NULL COMMENT '视频帧率',
  `video_aspectrate` VARCHAR(255) NULL COMMENT '视频长宽比',
  `video_scaling` VARCHAR(255) NULL COMMENT '视频伸缩',
  `video_definition` VARCHAR(255) NULL COMMENT '视频定义',
  `colourindicator` VARCHAR(255) NULL COMMENT '色彩指示器',
  `audio_bitrate` VARCHAR(255) NULL COMMENT '音频比特率',
  `audio_samplerate` VARCHAR(255) NULL COMMENT '音频频率',
	 * */
	private int id;
	private int contentId;
	private String headline;
	private String description;
	private String iconHref;
	private String iconImageType;
	private String iconImageWidth;
	private String iconImageHeight;
	private String iconRendition;
	private String scripts;
	private String language;
	private String href;
	private String videoMIMEType;
	private String format;
	private String rendition;
	private int size;
	private String duration;
	private String durationUnit;
	private String videoCodec;
	private String videoFramerate;
	private String videoAspectrate;
	private String videoScaling;
	private String videoDefinistion;
	private String colorIndicator;
	private String audioBitrate;
	private String audioSamplerate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconHref() {
		return iconHref;
	}
	public void setIconHref(String iconHref) {
		this.iconHref = iconHref;
	}
	public String getIconImageType() {
		return iconImageType;
	}
	public void setIconImageType(String iconImageType) {
		this.iconImageType = iconImageType;
	}
	public String getIconImageWidth() {
		return iconImageWidth;
	}
	public void setIconImageWidth(String iconImageWidth) {
		this.iconImageWidth = iconImageWidth;
	}
	public String getIconImageHeight() {
		return iconImageHeight;
	}
	public void setIconImageHeight(String iconImageHeight) {
		this.iconImageHeight = iconImageHeight;
	}
	public String getIconRendition() {
		return iconRendition;
	}
	public void setIconRendition(String iconRendition) {
		this.iconRendition = iconRendition;
	}
	public String getScripts() {
		return scripts;
	}
	public void setScripts(String scripts) {
		this.scripts = scripts;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getVideoMIMEType() {
		return videoMIMEType;
	}
	public void setVideoMIMEType(String videoMIMEType) {
		this.videoMIMEType = videoMIMEType;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getRendition() {
		return rendition;
	}
	public void setRendition(String rendition) {
		this.rendition = rendition;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDurationUnit() {
		return durationUnit;
	}
	public void setDurationUnit(String durationUnit) {
		this.durationUnit = durationUnit;
	}
	public String getVideoCodec() {
		return videoCodec;
	}
	public void setVideoCodec(String videoCodec) {
		this.videoCodec = videoCodec;
	}
	public String getVideoFramerate() {
		return videoFramerate;
	}
	public void setVideoFramerate(String videoFramerate) {
		this.videoFramerate = videoFramerate;
	}
	public String getVideoAspectrate() {
		return videoAspectrate;
	}
	public void setVideoAspectrate(String videoAspectrate) {
		this.videoAspectrate = videoAspectrate;
	}
	public String getVideoScaling() {
		return videoScaling;
	}
	public void setVideoScaling(String videoScaling) {
		this.videoScaling = videoScaling;
	}
	public String getVideoDefinistion() {
		return videoDefinistion;
	}
	public void setVideoDefinistion(String videoDefinistion) {
		this.videoDefinistion = videoDefinistion;
	}
	public String getColorIndicator() {
		return colorIndicator;
	}
	public void setColorIndicator(String colorIndicator) {
		this.colorIndicator = colorIndicator;
	}
	public String getAudioBitrate() {
		return audioBitrate;
	}
	public void setAudioBitrate(String audioBitrate) {
		this.audioBitrate = audioBitrate;
	}
	public String getAudioSamplerate() {
		return audioSamplerate;
	}
	public void setAudioSamplerate(String audioSamplerate) {
		this.audioSamplerate = audioSamplerate;
	}		
}

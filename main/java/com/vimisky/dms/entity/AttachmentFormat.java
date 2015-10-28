package com.vimisky.dms.entity;

/**
 * Refer to RFC 1700
 * Type            Subtype         Description                 Reference
----            -------         -----------                 ---------
text            plain                                   [RFC1521,NSB]
                richtext                                [RFC1521,NSB]
                tab-separated-values                   [Paul Lindner]

multipart       mixed                                   [RFC1521,NSB]
                alternative                             [RFC1521,NSB]
                digest                                  [RFC1521,NSB]
                parallel                                [RFC1521,NSB]
                appledouble                [MacMime,Patrik Faltstrom]
                header-set                             [Dave Crocker]

message         rfc822                                  [RFC1521,NSB]
                partial                                 [RFC1521,NSB]
                external-body                           [RFC1521,NSB]
                news                        [RFC 1036, Henry Spencer]

application     octet-stream                            [RFC1521,NSB]
                postscript                              [RFC1521,NSB]
                oda                                     [RFC1521,NSB]
                atomicmail                           [atomicmail,NSB]
                andrew-inset                       [andrew-inset,NSB]
                slate                           [slate,terry crowley]
                wita              [Wang Info Transfer,Larry Campbell]
                dec-dx            [Digital Doc Trans, Larry Campbell]
                dca-rft        [IBM Doc Content Arch, Larry Campbell]
                activemessage                          [Ehud Shapiro]
                rtf                                    [Paul Lindner]
                applefile                  [MacMime,Patrik Faltstrom]
                mac-binhex40               [MacMime,Patrik Faltstrom]
                news-message-id              [RFC1036, Henry Spencer]
                news-transmission            [RFC1036, Henry Spencer]
                wordperfect5.1                         [Paul Lindner]
                pdf                                    [Paul Lindner]
                zip                                    [Paul Lindner]
                macwriteii                             [Paul Lindner]
                msword                                 [Paul Lindner]
                remote-printing                         [RFC1486,MTR]

image           jpeg                                    [RFC1521,NSB]
                gif                                     [RFC1521,NSB]
                ief             Image Exchange Format       [RFC1314]
                tiff            Tag Image File Format           [MTR]

audio           basic                                   [RFC1521,NSB]

video           mpeg                                    [RFC1521,NSB]
                quicktime                              [Paul Lindner]
 * 
 * ***/

public class AttachmentFormat {

//	RFC 2046 introduced
	public static final String AF_PRIMARY_TEXT = "text";
	public static final String AF_PRIMARY_IMAGE = "image";
	public static final String AF_PRIMARY_AUDIO = "audio";
	public static final String AF_PRIMARY_VIDEO = "video";
	public static final String AF_PRIMARY_APPLICATION = "application";

//	RFC 1700 registed
	public static final String AF_SUBTYPE_TEXT_PLAIN = "plain";
	public static final String AF_SUBTYPE_TEXT_RICHTEXT = "richtext";
	public static final String AF_SUBTYPE_TEXT_TAB_SEPARATED_VALUES = "tab separated values";
//	not registed
	public static final String AF_SUBTYPE_TEXT_HTML = "html";
	public static final String AF_SUBTYPE_TEXT_JAVASCRIPT = "javascript";
	public static final String AF_SUBTYPE_TEXT_CSS = "css";
//	RFC 1700 registed
	public static final String AF_SUBTYPE_IMAGE_JPEG = "jpeg";
	public static final String AF_SUBTYPE_IMAGE_GIF = "gif";
	public static final String AF_SUBTYPE_IMAGE_IEF = "ief";
	public static final String AF_SUBTYPE_IMAGE_TIFF = "tiff";
//	RFC 1700 registed	
	public static final String AF_SUBTYPE_AUDIO_BASIC = "basic";
//	RFC 1700 registed	
	public static final String AF_SUBTYPE_VIDEO_MPEG = "mpeg";
	public static final String AF_SUBTYPE_VIDEO_QUICKTIME = "quicktime";
//	RFC 1700 registed	
	public static final String AF_SUBTYPE_APPLICATION_OCTET_STREAM = "octet-stream";
	public static final String AF_SUBTYPE_APPLICATION_POSTSCRIPT = "postscript";
	public static final String AF_SUBTYPE_APPLICATION_ODA = "oda";
	public static final String AF_SUBTYPE_APPLICATION_ATOMICMAIL = "atomicmail";
	public static final String AF_SUBTYPE_APPLICATION_ANDREW_INSET = "andrew-inset";
	public static final String AF_SUBTYPE_APPLICATION_SLATE = "slate";
	public static final String AF_SUBTYPE_APPLICATION_WITA = "wita";
	public static final String AF_SUBTYPE_APPLICATION_RTF = "rtf";
	public static final String AF_SUBTYPE_APPLICATION_PDF = "pdf";
	public static final String AF_SUBTYPE_APPLICATION_ZIP = "zip";
	public static final String AF_SUBTYPE_APPLICATION_MSWORD = "msword";
	public static final String AF_SUBTYPE_APPLICATION_MACWRITEII = "macwriteii";
	public static final String AF_SUBTYPE_APPLICATION_REMOTEPRINTING = "remote-printing";

	
	private String primaryType;
	private String subType;
	
	public AttachmentFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttachmentFormat(String primaryType, String subType) {
		super();
		this.primaryType = primaryType;
		this.subType = subType;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.primaryType + "/" + this.subType;
	}
	
	
}

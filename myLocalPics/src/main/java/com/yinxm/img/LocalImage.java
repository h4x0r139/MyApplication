package com.yinxm.img;

/**
 * Created by yinxm on 2017/2/23.
 * 功能:
 */

public class LocalImage extends MediaStoreBaseBean {
    /**
     * The description of the image
     * <P>Type: TEXT</P>
     */
    public   String description;

    /**
     * The picasa id of the image
     * <P>Type: TEXT</P>
     */
    public  String picasa_id;

    /**
     * Whether the video should be published as public or private
     * <P>Type: INTEGER</P>
     */
    public int isprivate;

    /**
     * The latitude where the image was captured.
     * <P>Type: DOUBLE</P>
     */
    public double latitude;

    /**
     * The longitude where the image was captured.
     * <P>Type: DOUBLE</P>
     */
    public double longitude;

    /**
     * The date & time that the image was taken in units
     * of milliseconds since jan 1, 1970.
     * <P>Type: INTEGER</P>
     */
    public int datetaken;

    /**
     * The orientation for the image expressed as degrees.
     * Only degrees 0, 90, 180, 270 will work.
     * <P>Type: INTEGER</P>
     */
    public int orientation;

    /**
     * The mini thumb id.
     * <P>Type: INTEGER</P>
     */
    public int mini_thumb_magic;

    /**
     * The bucket id of the image. This is a read-only property that
     * is automatically computed from the DATA column.
     * <P>Type: TEXT</P>
     */
    public String bucket_id;

    /**
     * The bucket display name of the image. This is a read-only property that
     * is automatically computed from the DATA column.
     * <P>Type: TEXT</P>
     */
    public String bucket_display_name;


}

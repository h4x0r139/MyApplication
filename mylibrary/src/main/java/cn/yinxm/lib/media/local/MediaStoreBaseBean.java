package cn.yinxm.lib.media.local;

/**
 * Created by yinxm on 2017/2/23.
 * 功能:MediaStore公共列
 */

public class MediaStoreBaseBean {

    /**
     * The unique ID for a row.
     * <P>Type: INTEGER (long)</P>
     */
    public int _id;

    /**
     * The count of rows in a directory.
     * <P>Type: INTEGER</P>
     */
    public int _count;

    /**
     * The data stream for the file
     * <P>Type: DATA STREAM</P>
     */
    public String _data;

    /**
     * The size of the file in bytes
     * <P>Type: INTEGER (long)</P>
     */
    public int _size;

    /**
     * The display name of the file
     * <P>Type: TEXT</P>
     */
    public String display_name;

    /**
     * The title of the content
     * <P>Type: TEXT</P>
     */
    public String title;

    /**
     * The time the file was added to the media provider
     * Units are seconds since 1970.
     * <P>Type: INTEGER (long)</P>
     */
    public long date_added;

    /**
     * The time the file was last modified
     * Units are seconds since 1970.
     * NOTE: This is for internal use by the media scanner.  Do not modify this field.
     * <P>Type: INTEGER (long)</P>
     */
    public long date_modified;

    /**
     * The MIME type of the file
     * <P>Type: TEXT</P>
     */
    public String mime_type;

    /**
     * The MTP object handle of a newly transfered file.
     * Used to pass the new file's object handle through the media scanner
     * from MTP to the media provider
     * For internal use only by MTP, media scanner and media provider.
     * <P>Type: INTEGER</P>
     * @hide
     */
    public int media_scanner_new_object_id;

    /**
     * Non-zero if the media file is drm-protected
     * <P>Type: INTEGER (boolean)</P>
     * @hide
     */
    public int is_drm;

    /**
     * The width of the image/video in pixels.
     */
    public int width;

    /**
     * The height of the image/video in pixels.
     */
    public int height;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MediaStoreBaseBean{");
        sb.append("_id=").append(_id);
        sb.append(", _data='").append(_data).append('\'');
        sb.append(", _size=").append(_size);
        sb.append(", display_name='").append(display_name).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append('}');
        return sb.toString();
    }
}

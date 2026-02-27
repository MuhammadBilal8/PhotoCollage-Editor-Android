package collagestudio.photocollage.collagemaker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import collagestudio.photocollage.collagemaker.R;
import collagestudio.photocollage.collagemaker.model.GalleryAlbum;
import java.util.List;

import dauroi.photoeditor.utils.PhotoUtils;


public class GalleryAlbumAdapter extends ArrayAdapter<GalleryAlbum> {
    public interface OnGalleryAlbumClickListener {
        void onGalleryAlbumClick(GalleryAlbum album);
    }

    private LayoutInflater mInflater;
    private OnGalleryAlbumClickListener mListener;

    public GalleryAlbumAdapter(Context context, List<GalleryAlbum> objects, OnGalleryAlbumClickListener listener) {
        super(context, R.layout.item_gallery_album, objects);
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gallery_album, parent, false);
            holder = new ViewHolder();
            holder.thumbnailView = (ImageView) convertView.findViewById(R.id.thumbnailView);
            holder.titleView = (TextView) convertView.findViewById(R.id.titleView);
            holder.itemCountView = (TextView) convertView.findViewById(R.id.itemCountView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        final GalleryAlbum item = getItem(position);
        if (item != null) {
            if (holder != null) {
                if (item.getImageList().size() > 0) {
                    PhotoUtils.loadImageWithGlide(getContext(), holder.thumbnailView, item.getImageList().get(0));
                } else {
                    holder.thumbnailView.setImageBitmap(null);
                }

                holder.titleView.setText(item.getAlbumName());
                holder.itemCountView.setText("(" + item.getImageList().size() + ")");
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onGalleryAlbumClick(item);
                        }
                    }
                });
            }
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView thumbnailView;
        TextView titleView;
        TextView itemCountView;
    }
}

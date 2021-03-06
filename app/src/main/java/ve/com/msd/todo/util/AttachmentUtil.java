package ve.com.msd.todo.util;

import java.util.Iterator;
import java.util.List;

import ve.com.msd.todo.model.attachment.Attachment;
import ve.com.msd.todo.model.attachment.AudioAttachment;
import ve.com.msd.todo.model.attachment.ImageAttachment;
import ve.com.msd.todo.model.attachment.LinkAttachment;
import ve.com.msd.todo.model.attachment.ListAttachment;
import ve.com.msd.todo.model.attachment.ListItemAttachment;
import ve.com.msd.todo.model.attachment.TextAttachment;



public class AttachmentUtil {

    public static void cleanInvalidAttachments(List<Attachment> attachments) {

        Iterator<Attachment> attachmentIterator = attachments.iterator();
        while(attachmentIterator.hasNext()) {

            Attachment attachment = attachmentIterator.next();
            switch (attachment.getType()) {
                case LINK:
                    if (((LinkAttachment) attachment).getLink() == null || ((LinkAttachment) attachment).getLink().isEmpty())
                        attachmentIterator.remove();
                    break;

                case TEXT:
                    if (((TextAttachment) attachment).getText() == null || ((TextAttachment) attachment).getText().isEmpty())
                        attachmentIterator.remove();
                    break;

                case AUDIO:
                    if (((AudioAttachment) attachment).getAudioFilename() == null || ((AudioAttachment) attachment).getAudioFilename().isEmpty())
                        attachmentIterator.remove();
                    break;

                case IMAGE:
                    if (((ImageAttachment) attachment).getImageFilename() == null || ((ImageAttachment) attachment).getImageFilename().isEmpty())
                        attachmentIterator.remove();
                    break;

                case LIST:
                    //Iterate Items, remove empty ones
                    Iterator<ListItemAttachment> i = ((ListAttachment) attachment).getItems().iterator();
                    while (i.hasNext()) {
                        ListItemAttachment item = i.next();
                        if (item.getText() == null || item.getText().isEmpty())
                            i.remove();
                    }

                    //If List Attachment has no items, delete the whole thing.
                    if (((ListAttachment) attachment).getItems().size() == 0) {
                        attachmentIterator.remove();
                    }
                    break;
            }
        }
    }

}

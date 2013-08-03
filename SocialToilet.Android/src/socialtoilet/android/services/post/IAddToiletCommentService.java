package socialtoilet.android.services.post;

import socialtoilet.android.model.Comment;

public interface IAddToiletCommentService
{
	void addToiletComment(IAddToiletCommentServiceDelegate delegate, String toiletId, Comment comment);
}

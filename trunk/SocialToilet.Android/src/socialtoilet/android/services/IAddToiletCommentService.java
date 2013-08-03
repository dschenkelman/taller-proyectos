package socialtoilet.android.services;

import socialtoilet.android.model.Comment;

public interface IAddToiletCommentService
{
	void addToiletComment(IAddToiletCommentServiceDelegate delegate, String toiletId, Comment comment);
}

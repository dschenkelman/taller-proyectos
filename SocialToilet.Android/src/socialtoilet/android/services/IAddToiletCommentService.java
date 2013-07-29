package socialtoilet.android.services;

import socialtoilet.android.model.Comment;

public interface IAddToiletCommentService
{
	void addToiletComment(IAddToiletCommentServiceDelegate delegate, Comment comment);
}

package socialtoilet.android.services;

import socialtoilet.android.model.Comment;

public interface IAddToiletCommentServiceDelegate
{
	void addToiletCommentFinish(IAddToiletCommentService service, Comment addedComment);
	void addToiletCommentFinishWithError(IAddToiletCommentService service, String errorCode);
}

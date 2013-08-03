package socialtoilet.android.activities;

import socialtoilet.android.R;
import socialtoilet.android.activities.dialogs.ErrorDialogFragment;
import socialtoilet.android.activities.dialogs.ErrorDialogFragment.IErrorDialogDataSource;
import socialtoilet.android.model.LoginUser;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.services.post.IAuthService;
import socialtoilet.android.services.post.IAuthServiceDelegate;
import socialtoilet.android.utils.Settings;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class StartSessionActivity extends FragmentActivity 
	implements IAuthServiceDelegate, IErrorDialogDataSource
{
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	// Values for email and password at the time of the login attempt.
	private String mUser;
	private String mPassword;
	private boolean attemptingLogin;

	// UI references.
	private EditText mUserView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_session);

		// Set up the login form.
		mUser = getIntent().getStringExtra(EXTRA_EMAIL);
		mUserView = (EditText) findViewById(R.id.user);
		mUserView.setText(mUser);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
		
		if(Settings.getInstance().isSessionOn())
		{
	    	Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
		}
	}

	public void attemptLogin()
	{
		if (attemptingLogin)
		{
			return;
		}
		attemptingLogin = true;
		
		// Reset errors.
		mUserView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mUser = mUserView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(mPassword))
		{
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		}
		else if (mPassword.length() < 4)
		{
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		if (TextUtils.isEmpty(mUser))
		{
			mUserView.setError(getString(R.string.error_field_required));
			focusView = mUserView;
			cancel = true;
		}

		if (cancel)
		{
			focusView.requestFocus();
		}
		else
		{
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			
			LoginUser user = new LoginUser(mUser, mPassword);
			Settings.getInstance().setAuthUser(user);
			IAuthService service = ServicesFactory.createAuthService();
			service.authUser(this, user);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show)
	{
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
		{
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter()
					{
						@Override
						public void onAnimationEnd(Animator animation)
						{
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter()
					{
						@Override
						public void onAnimationEnd(Animator animation)
						{
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		}
		else
		{
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public void authServiceDelegateFinish(
			IAuthService service, LoginUser user)
	{
		attemptingLogin = false;
		showProgress(false);
		Settings.getInstance().setUser(user);
    	Intent intent = new Intent(this, MappingToiletActivity.class);
    	startActivity(intent);
	}

	@Override
	public void authServiceDelegateFinishWithError(
			IAuthService service, String errorCode)
	{
		showProgress(false);
		attemptingLogin = false;

		ErrorDialogFragment dialog = new ErrorDialogFragment();
    	dialog.show(getSupportFragmentManager(), "error");
	}

	@Override
	public String getErrorMessage()
	{
		return "Credenciales inválidas";
	}

	@Override
	public String getErrorTitle()
	{
		return "Error";
	}
}

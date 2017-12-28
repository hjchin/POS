package pos.com.pos;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pos.com.pos.library.LibraryFragment;
import pos.com.pos.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by HJ Chin on 28/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testShowLibrary(){
        onView(withText(LibraryFragment.libraryItemArrayList.get(0).name)).check(matches(isDisplayed()));
        onView(withText(LibraryFragment.libraryItemArrayList.get(1).name)).check(matches(isDisplayed()));
    }
}

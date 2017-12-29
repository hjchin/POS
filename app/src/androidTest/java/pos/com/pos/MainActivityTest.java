package pos.com.pos;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pos.com.pos.allItems.view.ItemListFragment;
import pos.com.pos.discount.view.DiscountListFragment;
import pos.com.pos.library.view.LibraryFragment;
import pos.com.pos.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by HJ Chin on 28/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testShowLibrary(){
        onView(withText(activityTestRule.getActivity().getString(R.string.library))).check(matches(isDisplayed()));
        onView(withText(LibraryFragment.libraryItemArrayList.get(0).name)).check(matches(isDisplayed()));
        onView(withText(LibraryFragment.libraryItemArrayList.get(1).name)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowDiscountFragment(){
        onView(withText(LibraryFragment.libraryItemArrayList.get(0).name)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withText(activityTestRule.getActivity().getString(R.string.all_discounts))).check(matches(isDisplayed()));
        onView(withText(DiscountListFragment.discountItemArrayList.get(0).name)).check(matches(isDisplayed()));

        activityTestRule.getActivity().backStack();
        onView(withText(activityTestRule.getActivity().getString(R.string.library))).check(matches(isDisplayed()));
        onView(withText(LibraryFragment.libraryItemArrayList.get(0).name)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowAllItemsFragment(){

        IdlingRegistry.getInstance().register(activityTestRule.getActivity().getIdlingCounter());

        onView(withText(LibraryFragment.libraryItemArrayList.get(1).name)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        onView(withText(activityTestRule.getActivity().getString(R.string.all_items))).check(matches(isDisplayed()));

        onView(withText("accusamus beatae ad facilis cum similique qui sunt")).check(matches(isDisplayed()));

        activityTestRule.getActivity().backStack();
        onView(withText(activityTestRule.getActivity().getString(R.string.library))).check(matches(isDisplayed()));
        onView(withText(LibraryFragment.libraryItemArrayList.get(0).name)).check(matches(isDisplayed()));

    }
}

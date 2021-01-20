
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.R.id.list_favorite;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule = new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on a list item, displays the Activity details
     */
    @Test
    public void myNeighboursList_clickOnItemList_shouldDisplayViewDetails() {
        // Given : We lunch elements these list.
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on element number 0.
        onView(withId(R.id.list_neighbours)).perform(actionOnItemAtPosition(0, click()));
        // Then : the number of element 0 is displaying on activity details.
        onView(withId(R.id.activity_details)).check(matches(isDisplayed()));

    }

    /**
     * at starting the new screen, the TextView indicating The name of the user is well filled
     */
    @Test
    public void activityDetails_indicatingTheName_shouldBeFill() {
        // Given : We click the element at position 0
        onView(withId(R.id.list_neighbours)).perform(actionOnItemAtPosition(0, click()));
       // When view details displays
        onView(withId(R.id.activity_details)).check(matches(isDisplayed()));
        // Then : textview indicating the number of element 0 is not filled
        onView(withId(R.id.activity_name_users)).check(matches(withText("Caroline")));

    }

    /**
     * the Favorites tab only shows neighbours marked as Favorites
     */
    @Test
    public void myFavoritesList_displaysTheTabFavorites () {
        // Given : We click the element at position 1
        onView(withId(R.id.list_neighbours)).perform(actionOnItemAtPosition(0, click()));
       // When perform a click on a favorite and return icon
        onView((withId(R.id.floatingActionButton_favorites))).perform(click());
        onView((withId(R.id.activity_button_back))).perform(click());
        onView(withId(R.id.list_neighbours)).perform(actionOnItemAtPosition(1, click()));
        onView((withId(R.id.floatingActionButton_favorites))).perform(click());
        onView((withId(R.id.activity_button_back))).perform(click());
      // Then : View the tab favorites
        onView(ViewMatchers.withId(R.id.container)).perform(scrollRight());
        onView(withId(R.id.list_favorite)).check(matches(hasMinimumChildCount(2)));

    }
    /**
     *When we delete an favorites list item, the item is no more shown
     */
    @Test
    public void myFavoritesList_deleteAction_shouldRemoveItem() {
        // Given : We click the element at position 1
        onView(withId(R.id.list_neighbours)).perform(actionOnItemAtPosition(1, click()));
        // When perform a click on a favorite and return icon
        onView((withId(R.id.floatingActionButton_favorites))).perform(click());
        onView((withId(R.id.activity_button_back))).perform(click());
        //then : change tabs scroll favorite.
        onView(withId(R.id.container)).perform(scrollRight());
        // Given : element of list favorite.
        onView(withId(list_favorite)).check(withItemCount(1));
        // When perform a click on a delete icon
        onView(withId(list_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of element is 0
        onView(ViewMatchers.withId(list_favorite)).check(withItemCount(0));

    }
}
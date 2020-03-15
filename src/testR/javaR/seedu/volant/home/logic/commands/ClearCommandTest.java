package seedu.volant.home.logic.commands;

import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalTrips.getTypicalTripList;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.Model;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.TripList;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new HomeModelManager();
        Model expectedModel = new HomeModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new HomeModelManager(getTypicalTripList(), new UserPrefs());
        Model expectedModel = new HomeModelManager(getTypicalTripList(), new UserPrefs());
        expectedModel.setTripList(new TripList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

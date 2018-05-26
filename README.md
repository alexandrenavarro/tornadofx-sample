# tornadofx-sample
tornadofx-sample

# To build
./gradlew build

Caution, it uses actually the last version of tornadofx 1.7.17-SNAPSHOT, you have to compile tornadofx it first.

# To run
Run com.github.alexandrenavarro.tornadofxsample.CountryApp


# To test / find solution :
- Init with tornado fx : DONE
- Add a feign resource with correct dependencies : DONE
- Use Model / View / ViewModel Pattern : DONE
    - The View knows the ViewModel but not the Model.
    - The ViewModel knows the Model but not the View (store state and operations of the view)
    - The Model knows neither.
- Use runAsync {} ui {} for DONE
- Update table for another thread DONE (but need to run if modify observableList binded to TableView)
- Test master detail DONE
- Ioc for Feign use spring or tornado.di DONE
- Update CountryEditViewMode -> CountryItemViewModel : DONE
- Add Unit test for ViewModel : DONE
- Add unit test with testfx for :  DONE but missed PR on https://github.com/edvin/tornadofx/issues/728
- Compare RxKotlinFX or ReactorFX on action vs coroutine vs runAsync {} ui {}, DONE runAsync ui is simpler than coroutine to understand and suffisant in most of the cases
- check import (kotlin parser / klint Rule), View must not have *Resource, ViewModel must not have View and only javafx.beans for install TODO




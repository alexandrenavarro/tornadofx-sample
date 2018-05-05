# tornadofx-sample
tornadofx-sample

To test / find solution :
- Init with tornado fx : DONE
- Add a feign resource : DONE
- Use Model / View / ViewModel : DONE
    - The View knows the ViewModel but not the Model.
    - The ViewModel knows the Model but not the View (store state and operations of the view)
    - The Model knows neither.
- Use runAsync {} ui {} for DONE
- Update table for another thread DONE (but need to run if modify observableList binded to TableView)
- Test master detail DONE
- Ioc for Feign use spring or tornado.di
- Test RxKotlinFX or ReactorFX on action vs runAsync {} ui {}


Find solution
- easily transform POJO -> javafx property,
    - just generate class like FxCountry, problem extentions method not import,
    - generic FxPojo<xxx>  ?
    - use setCellValueFactory (how)
- check import (kotlin parser), View must not have *Resource, ViewModel must not have View and only javafx.beans for install




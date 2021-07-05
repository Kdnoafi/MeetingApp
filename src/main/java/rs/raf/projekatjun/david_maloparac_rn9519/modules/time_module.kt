package rs.raf.projekatjun.david_maloparac_rn9519.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekatjun.david_maloparac_rn9519.data.time.TimeService
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.viewmodel.TimeViewModel

val timeModule = module {

    viewModel {
        TimeViewModel(
                source = get()
        )
    }

    single<TimeService> { create(retrofit = get()) }
}
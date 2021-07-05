package rs.raf.projekatjun.david_maloparac_rn9519.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventDataBase
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventRepository
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventRepositoryImpl
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.viewmodel.EventViewModel

val eventModule = module {

    viewModel { EventViewModel(eventRepository = get()) }

    single<EventRepository> { EventRepositoryImpl(localDataSource = get()) }

    single { get<EventDataBase>().eventDao() }
}

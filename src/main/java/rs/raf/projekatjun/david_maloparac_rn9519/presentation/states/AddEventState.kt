package rs.raf.projekatjun.david_maloparac_rn9519.presentation.states

sealed class AddEventState {

    object Success: AddEventState()
    data class Error(val message: String): AddEventState()
}
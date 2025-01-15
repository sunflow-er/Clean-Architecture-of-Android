// UI Layer
viewModel.data.observe(viewLifecycleOwner) { resource ->
    when(resource) {
        is DataResource.Loading -> showLoading()
        is DataResource.Success -> displayData(resource.data)
        is DataResource.Error -> showError(resource.exception)
    }
}
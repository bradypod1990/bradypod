function(key, values) {
	var names = "";
	for (var i = 0; i < values.length; i++) {
		names += values[i] + "-";
	}
    return names;
}
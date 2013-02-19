package es.regueiro.lyricsDownloader.plugins;

/**
 * The abstract plugin class.
 * 
 * @author Faheem
 * 
 */
public abstract class Plugin {

	private String name;

	/**
	 * plugin template method
	 */
	public void run() {
		// setting up the plugin
		name = setup();
		// performing the plugin action
		performAction();
		// performing cleanup actions.
     tearDown();
}

protected abstract String setup();
protected abstract void performAction();
protected abstract void tearDown();
}
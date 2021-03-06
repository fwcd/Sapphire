package fwcd.sapphire.utils;

import java.io.InputStream;

import fwcd.sapphire.exception.FileException;

/**
 * Represents some content that can be
 * read out through an InputStream.
 * 
 * @author fwcd
 *
 */
public interface AnyFile {
	/**
	 * <p>Performs an action using the
	 * open input stream to produce a result.</p>
	 * 
	 * <p>This method inherently prevents resource leaks by not
	 * exposing the InputStream to other classes.</p>
	 * 
	 * @param action - The action that produces a result
	 * @return A result
	 * @throws FileException if anything goes wrong
	 */
	<T> T mapStream(IOFunction<InputStream, T> action);
	
	/**
	 * <p>Performs an action/a procedure using the
	 * open input stream.</p>
	 * 
	 * <p>This method inherently prevents resource leaks by not
	 * exposing the InputStream to other classes.</p>
	 * 
	 * @param action - The action/procedure
	 * @throws FileException if anything goes wrong
	 */
	default <T> void withStream(IOConsumer<InputStream> action) {
		mapStream(in -> {
			action.accept(in);
			return (Void) null;
		});
	}
}

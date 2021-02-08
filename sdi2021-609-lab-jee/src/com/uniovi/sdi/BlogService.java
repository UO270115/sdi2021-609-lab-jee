package com.uniovi.sdi;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class BlogService {

	public List<Comentario> getComentarios() {
		List<Comentario> comentarios = new LinkedList<Comentario>();
		ObjectContainer db = null;
		try {
			db = Db4oEmbedded.openFile("bdBlog");
			List<Comentario> respuesta = db.queryByExample(Comentario.class);
			// NO RETORNAR LA MISMA LISTA DE LA RESPUESTA
			comentarios.addAll(respuesta);
		} finally {
			db.close();
		}
		return comentarios;
	}

	public void setNuevoComentario(Comentario nuevoComentario) {
		ObjectContainer db = null;
		try {
			db = Db4oEmbedded.openFile("bdBlog");
			db.store(nuevoComentario);
		} finally {
			db.close();
		}
	}

}

package br.gov.frameworkdemoiselle;

import static javax.servlet.http.HttpServletResponse.SC_PRECONDITION_FAILED;

import java.util.HashSet;
import java.util.Set;

import javax.xml.ws.http.HTTPException;

public class PreconditionFailedException extends HTTPException {

	private static final long serialVersionUID = 1L;

	private Set<Violation> violations = new HashSet<Violation>();

	public PreconditionFailedException() {
		super(SC_PRECONDITION_FAILED);
	}

	public PreconditionFailedException addViolation(String property, String message) {
		this.violations.add(new Violation(property, message));
		return this;
	}

	public Set<Violation> getViolations() {
		return violations;
	}

	public static class Violation {

		public String property;

		public String message;

		public Violation() {
		}

		public Violation(String property, String message) {
			this.property = property;
			this.message = message;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((message == null) ? 0 : message.hashCode());
			result = prime * result + ((property == null) ? 0 : property.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Violation other = (Violation) obj;
			if (message == null) {
				if (other.message != null)
					return false;
			} else if (!message.equals(other.message))
				return false;
			if (property == null) {
				if (other.property != null)
					return false;
			} else if (!property.equals(other.property))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return this.property + " " + this.message;
		}
	}
}
